from flask import Flask, jsonify, request
import mysql.connector
import datetime

conn = mysql.connector.connect(user="root",
                               password="0000",
                               host="localhost",
                               port="3307",
                               database="question")
now = datetime.datetime.now()
app = Flask(__name__)


@app.route('/')
def hello_world():
    return 'Hello World!'


def get_json(script):
    cursor = conn.cursor()
    cursor.execute(script)
    row_headers = [x[0] for x in cursor.description]
    rv = cursor.fetchall()
    json_data = []
    for result in rv:
        json_data.append(dict(zip(row_headers, result)))
    res = {"answer": json_data}
    return jsonify(res)


@app.route('/api/news', methods=['GET', 'POST'])
def get_all_news():
    if request.method == 'GET':
        script = "select question, answer from data"
        result = get_json(script)
        return result

    elif request.method == 'POST':
        name = request.json['name']
        surname = request.json['surname']
        phone = request.json['phone']
        email = request.json['email']
        question = request.json['question']
        data = now.strftime("%Y-%m-%d %H:%M")
        cursor = conn.cursor()
        cursor.execute("INSERT INTO data (name, surname, phone, email,  question, data)VALUES(%s,%s,%s, %s,%s,%s)",
                       (name, surname, phone, email, question, str(data)))
        conn.commit()
        return "Data add"


@app.route('/api/news/addAnswer/<id>', methods=['POST'])
def addAnswer(id):
    answer = '"' + request.json['answer'] + '"'
    cursor = conn.cursor()
    cursor.execute("Update data SET answer=" + answer + " where id = " + id)

    conn.commit()
    return "Otvet prinyat"


if __name__ == '__main__':
    app.debug = True
    app.run(host='172.16.85.180', port=8000)

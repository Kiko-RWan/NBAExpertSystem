from flask import Flask, render_template, request, jsonify
from rate import rate

app = Flask(__name__)

results = []

@app.route('/', methods=['GET', 'POST'])
def index():
    if request.method == 'GET':
        return render_template("questions.html")
    else:
        user_player = {}
        user_player['pos'] = request.form.getlist('check')
        user_player['shooting'] = request.form['shooting']
        user_player['leading'] = request.form['leading']
        user_player['defensive'] = request.form['defensive']
        user_player['breaking'] = request.form['breaking']
        user_player['rebounding'] = request.form['rebounding']

        results = rate(user_player)

        return render_template("results.html")


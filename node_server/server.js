const express = require("express");
const bodyParser = require("body-parser");

const app = express()
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.get('/', (req, res) => res.send('Hello World!'));

app.post("/send_order", (req, res) => {
  console.log('received');
  let data = req.body;
  console.log(data);
  let reply = {
    msg: "Received"
  }
  res.send(reply);
});

app.listen(3000, () => console.log('Listening to port 3000'));

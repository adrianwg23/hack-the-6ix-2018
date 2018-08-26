const express = require("express");
const bodyParser = require("body-parser");

const app = express()

const http = require('http').Server(app);
const io = require('socket.io')(http);
app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());

app.use(express.static('webapp'))

app.post("/send_order", (req, res) => {
  console.log('received');
  let data = req.body;

  io.emit('ordered', data);
  
  let reply = {
    msg: "Received"
  }
  res.send(reply);
});

// app.listen(3000, () => console.log('Listening to port 3000'));
http.listen(3000, function(){
  console.log('listening on *:3000');
});

io.on('connection', function(socket){
  console.log('a user connected');
  socket.on('disconnect', function(){
    console.log('user disconnected');
  });
});

const lib = require('lib')({
  token: process.env.STDLIB_TOKEN
});
const send = require('../../helpers/send.js');
const firebase = require("firebase-admin");
const serviceAccount = require("../../serviceAccountKey.json");
const request = require('request');
const axios = require('axios');
// require('es6-promise').polyfill();
// require('isomorphic-fetch');

let db = null;

/**
 * MORE handler, responds if user texts "more"
 *  (or any uppercase variation like "MORE")
 * @param {string} sender The phone number that sent the text to be handled
 * @param {string} receiver The StdLib phone number that received the SMS
 * @param {string} message The contents of the SMS
 * @param {string} createdDatetime Datetime when the SMS was sent
 * @returns {any}
 */
module.exports = async (sender = '', receiver = '', message = '', createdDatetime = '', context) => {
  // Initialize firebase app
  if (!db) {
    await firebase.initializeApp({
      credential: firebase.credential.cert(serviceAccount),
      databaseURL: "https://hack-the-6ix-201-1535217409242.firebaseio.com/"
    });
    db = await firebase.database()
  }

  let parsedMessage = message.split(" #").map((word) => word.trim().replace("#", ""));
  if (parsedMessage.length == 1) {
    return send(
      receiver,
      sender,
      `Please specify a <restaurant_name> after the command <order>`
    )
  } else if (parsedMessage.length == 2) {
    return send(
      receiver,
      sender,
      `Please specify a <menu_item> after the command <restaurant_name>`
    )
  }

  let restaurantName = parsedMessage[1];
  let menu_item = parsedMessage[2];

  // Check database for menu_item
  let ref = db.ref("restaurants/" + restaurantName + "/menu");
  let snapshot = await ref.once("value");
  let dataJSON = snapshot.val();
  let menuItem = [];

  for (const key of Object.keys(dataJSON)) {
    menuItem.push(key);
  }
  if (menuItem.includes(menu_item)) {
    // Make HTTP request HERE
    console.log(sender)

    data = {
      sender: sender,
      restaurant: restaurantName,
      order: menu_item
    }

    let res = await axios.post('https://5bbee879.ngrok.io/send_order', data)
    console.log(res.data);

    return send(
        receiver,
        sender,
        `Your order has been successfully placed to ${restaurantName}. Thank you for your purchase`
      )
  } else {
    return send(
      receiver,
      sender,
      `The order was place unsucessfully, please make sure you selected a valid menu item.`
    )
  }
}

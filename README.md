# hack-the-6ix-2018 (nibble)

## Inspiration

We found our inspiration during a recent trip to New York City. It was restaurant week and hundreds of restaurants aimed to put forth the most bang for your buck meals. Unfortunately, we did not have data making it difficult to view restaurant locations, hours, promotions, and menus when out on the street. We realized two things. Firstly, there are many people like us who do not have data and wifi capabilities on the go. Secondly, in today’s modern society, people prefer texting rather than calling. So we combined these ideas to create a platform that allows customers to order food, book reservations, and view restaurant details all offline through text-message.

## What it does

Any user (with cellular coverage) can first send a text-message to the stdlib server. The text-message is parsed for keywords and uses those commands to display relevant information. For example, we implemented handlers that are able to display all available restaurants, display details (hours, wait time, restaurant location), display the restaurant menu and their prices, order a food item in which the order is sent to the web-application, display available reservation booking times, and lastly reserve a time slot. Once the user gives a valid command to the stdlib server, the respective command is carried and queries firebase for the correct information. If the user orders a food item through text-message, stdlib will then send a request with all the details to a separate node application used by the restaurants. By implementing websockets, the restaurants are able to use the frontend of the web-app to display all incoming food orders in real-time with all of the sender’s information. Finally, all the restaurant needs to do is cook the food and wait for the customer to pick it up. Easy!

## Challenges we ran into

A majority of the challenges we ran into involved Node. In particular, we had two major problems. The first one was that when we deployed our stdlib application, we could not receive a response from the server when texting the provided phone number using a real phone. However, when we texted the number through the terminal, we got a response just as we were expecting. We managed to narrow down the issue to the fact that we were initializing Firebase in the main.js file. This worked locally since the the Firebase instance once shared throughout other files when run locally. However, once deployed, the Firebase instance was not being shared to our handlers, so we had to initialize Firebase in each of our handlers to fix this issue. A second issue we had was making a post request in Node to our own local server. We first used a Node library called “request”. However, this library would not work for reasons we could not pinpoint. We ended up switching to another library called “axios” which managed to solve the issue.

## Accomplishments that we're proud of

Since it was our first time diving deep with node.js, we didn’t come in the hackathon with tons of experience with javascript and ES6. However, we were able to complete the whole project and get an awesome experience out of it. One thing we are proud of is being able to make the finished product have a streamlined approach and being able to architect it well so that all the components worked nicely with each other. We definitely enjoyed making this project and learning new things.

## What we learned

Over the course of using all these javascript platforms, we now have a better understanding about promises and how to correctly implement them so they can work nicely with the rest of the structure. We learned about how to structure NoSQL databases such as FireBase and how to implement them. Also, we learned that node.js isn’t always friendly and that packages with poor documentation can be a real pain.

## What's next for nibble

Cross platform capabilities with Android and iOS apps in order for the restaurants to view information easier. Also the ability to decode complex sentence structures/slang using Natural Language Processing to decipher a command, rather than relying on the user being forced to follow a template.


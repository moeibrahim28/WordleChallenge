# WordleChallenge

Just make it work. With the starter-code, you can see a Wordle screen. Type a word in the bottom of the screen and press enter.



// TODO

The Model shouldn't do anything related to display. It shouldn't even know that the application uses Swing. All of the JOptionPane stuff can be moved to another class in the UI package. There are a couple things you may need to do: 
1) the model could pass error messages (maybe WordleErrorDTO?) to observers when something goes wrong. A view class could observe those and then display error messages. 
2) Add more information to the WordleDTO like "status": "WORD_NOT_FOUND" or "status": "GAME_WON" and/or "error": {...information about the error...}

Consider a StatisticsService that also observes the Model. It can update the user.json whenever the Model changes.

Consider a StatisticsView class in the ui package. It can also observe the model. Whenever a WordleDTO with status GAME_WON or GAME_LOST is received, it can do the JOptionPane.showMessageDialog("yay, you win") or JOptionPane.showMessageDialog("oh no, you lose").

Your UserOutputService doesn't appear to really be used. If it were, it would just save the toString of User, which is not JSON.

From a UI perspective, it would be best if the "used letter" area displayed the letters that I've used that are totally wrong and the letters I've used that are in the wrong place and the letters I've used that are in the right place.
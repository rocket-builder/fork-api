# fork-api
 java spring restful api for fork bot
 > token is user hash(login + hashed password)  
 > bot token is user token
 
 ## authorization
 ### login
 > create user session on server side
 * method: POST
 * params: login(string), password(string)
 * result: user(object)
 ### logout
 > invalidate user session on server side
 * method: POST
 * params: none
 * result: ok(string)
 ## user
 ### get.user
 > access only for admin and user owner
 * method: GET
 * params: id(long), token(string)
 * result: user(object)
 ### add.user
 > access only for admins
 * method: POST
 * params: token(string), login(string), password(string), subscribe_end_date('YYYY-mm-dd' formatted string with date)
 * result: user(object)
 ### delete.user
 > access only for admins
 * method: POST
 * params: token(string), user_id(long)
 * result: ok(string)
 ### user.setSettings
 > access only for user profile owner, only after login  
 > balance_percent is percent from balance who will use in every bet placement
 * method: POST
 * params: balance_percent(int)
 * result: user(object)
 ## bookmaker
 ### get.bookmaker
 > access only for users from db
 * method: GET
 * params: token(string), id(long)
 * result: bookmaker(object)
 ### get.bookmakers
 > access only for users from db
 * method: GET
 * params: token(string)
 * result: array of bookmaker(object)
 ## add.bookmaker
 > access only for admins
 * method: POST
 * params: token(string), title(string), link(string with url adress on bookmaker site)
 * result: bookmaker(object)
 ## delete.bookmaker
 > access only for admins
 * method: POST
 * params: token(string), link(string with url adress on bookmaker site)
 * result: ok(string)
 ## bk account
 ### get bk accounts by user id
 ### update bk account balance by id
 
## bet
### add bet

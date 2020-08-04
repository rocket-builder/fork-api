# fork-api
 java spring restful api for fork bot
 > token is user hash(login + hashed password)
 > bot token is user token
 
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
 
 ## bk account
 ### get bk accounts by user id
 ### update bk account balance by id
 
## bet
### add bet

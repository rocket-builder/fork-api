# fork-api
 java spring restful api for fork bot
 > token is user hash(login + hashed password)  
 > bot token is user token  
 > api host: https://cors-anywhere.herokuapp.com/fork-api.herokuapp.com/
 
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
 ### get.users
 > access only for admin
 * method: GET
 * params: token(string)
 * result: array of user(object)
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
 > access only for user profile owner
 > balance_percent is percent from balance who will use in every bet placement
 * method: POST
 * params: balance_percent(int), token(string)
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
 * result: bookmakers(object array)
 ### add.bookmaker
 > access only for admins
 * method: POST
 * params: token(string), title(string), link(string with url adress on bookmaker site)
 * result: bookmaker(object)
 ### delete.bookmaker
 > access only for admins
 * method: POST
 * params: token(string), link(string with url adress on bookmaker site)
 * result: ok(string)
 ## bk account
 ### user.addBkAccount
 > access only for user profile owner
 * method: POST
 * params: bkUrl(string with url adress on bookmaker site), login(string), password(string), token(string)
 * result: bkAccount(object)
 ### user.deleteBkAccount
 > access only for user profile owner
 * method: POST
 * params: bk_account_id(long), token(string)
 * result: ok(string)
 ### user.setBkAccountBalance
 > only for user fork bot  
 * method: POST
 * params: token(string), bk_account_id(long), balance(float)
 * result: user(object)
 ## bet
 ### add.bet
 > only for user fork bot  
 * method: POST
 * params: token(string), bk_account_id(long), match_title(string), team(string), coefficient(float), bet_sum(float), bet_date('YYYY-mm-dd' formatted string with date)
 * result: bet(object)
 ### get.bets
 > get bets from all bk accounts from token owner user
 * method: GET
 * params: token(string)
 * result: bets(object array)
 ### get.betsFromSingleBkAccount
 > get bets from single bk accounts from token owner user
 * method: GET
 * params: token(string), bk_account_id(long)
 * result: bets(object array)

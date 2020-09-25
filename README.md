# fork-api
 java spring restful api for fork bot
 > token is user hash(login + hashed password)  
 > bot token is user token  
 > api host: https://cors-anywhere.herokuapp.com/fork-api.herokuapp.com/
 
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
 * params: token(string), login(string), password(string), subscribe_end_date('YYYY-mm-dd' formatted string with date), role(ADMIN or USER)(string)
 * result: user(object)
 ### delete.user
 > access only for admins
 * method: POST
 * params: token(string), user_id(long)
 * result: ok(string)
  ### ban.user
 > access only for admins, set is_banned field in User entity to true
 * method: POST
 * params: token(string), user_id(long)
 * result: ok(string)
  ### unban.user
 > access only for admins, set is_banned field in User entity to false
 * method: POST
 * params: token(string), user_id(long)
 * result: ok(string)
 ### user.setSettings
 > access only for user profile owner
 > balance_percent is percent from balance who will use in every bet placement
 * method: POST
 * content type: application/json, charset=utf-8
 * params: token(string)
 * data:  
 balance_percent(int), forks_live_time(int), fork_profit_percent_min(int), fork_profit_percent_max(int),  
 fork_done_try_cooldown(int), fork_cancel_try_cooldown(int),  
 fork_second_bet_timeout(int),  
 fork_not_closed_cooldown(int),  
 try_time_max(int), games(string array), markets(string array)  
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
 ### get.bkAccount
 > get bk account object  
 * method: GET
 * params: token(string), bk_account_id(long)
 * result: bkAccount(object)
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
 ### user.setBkAccountSettings
 > only for user fork bot  
 * method: POST
 * content type: application/json, charset=utf-8
 * params: token(string), bk_account_id(long)
 * data: bkMirror(string), rounding(int), bet_sum_min(int), bet_sum_max(int), cf_min(int), cf_max(int)
 * result: user(object)
 ## bet
 > get bets from all bk accounts from token owner user
 * method: GET
 * params: token(string)
 * result: bets(object array)
 ### get.betsFromSingleBkAccount
 > get bets from single bk accounts from token owner user
 * method: GET
 * params: token(string), bk_account_id(long)
 * result: bets(object array)
 ## forks
 ### add.fork
 > only for user fork bot  
 * method: POST
 * params: 
  token(string), 
  left_bk_account_id(long),   
  left_match_title(string),   
  left_team(string),   
  left_coefficient(float),   
  left_bet_sum(float),  
  left_bet_date('YYYY-mm-dd' formatted string with date),  
  
  right_bk_account_id(long),  
  right_match_title(string),  
  right_team(string),  
  right_coefficient(float),  
  right_bet_sum(float),  
  right_bet_date('YYYY-mm-dd' formatted string with date),  
 * result: fork(object)  
 ### user.getForks
 > get all forks from user account
 * method: GET
 * params: token(string)
 * result: forks(object array)
 ### bkAccount.getForks
 > get all forks from signle bk account
 * method: GET
 * params: token(string), bk_account_id(int)
 * result: forks(object array)
 ### get.users.forks
 > get all forks from all users, only for admins
 * method: GET
 * params: token(string)
 * result: forks(object array)
 ## stats
 ### get.users.stats
 > get sum of all profit stats from all users for day, week, month, only for admins
 * method: GET
 * params: token(string)
 * result: profit(object)
 ### get.users.statsList
 > get list of sum of all profit stats from all users for day, week, month, only for admins
 * method: GET
 * params: token(string)
 * result: profit(object array)
 ### get.user.stats
 > get sum of all profit stats from single users for day, week, month, only for admins
 * method: GET
 * params: token(string), id(int)
 * result: profit(object)
 ### user.getStats
 > get all profit stats from single user
 * method: GET
 * params: token(string)
 * result: profit(object) 


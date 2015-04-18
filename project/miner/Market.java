package qgb.project.miner;

import java.io.InputStream;

import qgb.*;
import qgb.net.Get;
import qgb.net.HttpRequest;
/**
 * https://jiaoyi.yunfan.com/index.php/transaction/get_market_overview
 * https://jiaoyi.yunfan.com/index.php/transaction/get_market_overview/5
 * https://jiaoyi.yunfan.com/index.php/transaction/get_market_overview_day/90
 * 2014-06-07 23:50:20
 * */
public class Market {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String sturl="https://jiaoyi.yunfan.com/market/24.html";
		sturl="https://jiaoyi.yunfan.com/index.php/transaction/get_market_overview";
		//InputStream is=
		
		U.write("market.html", HttpRequest.get(sturl));
	}

}

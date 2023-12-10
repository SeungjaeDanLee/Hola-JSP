 package com.team2.controller;

import com.team2.controller.action.Action;
import com.team2.controller.action.BoardListAction;
import com.team2.controller.action.BoardUpdateAction;
import com.team2.controller.action.BoardUpdateFormAction;
import com.team2.controller.action.BoardViewAction;
import com.team2.controller.action.BoardWriteAction;
import com.team2.controller.action.BoardWriteFormAction;
import com.team2.controller.action.MypageAction;

public class ActionFactory {
	private static ActionFactory instance = new ActionFactory();
	
	private ActionFactory() {
		super();
	}
	// 생성된 객체를 getInstance로 호출
		public static ActionFactory getInstance() {
			return instance;
		}
		
		public Action getAction(String command) {
			Action action = null;
			System.out.println("ActionFactory : " + command);
			
			if(command.equals("board_list")) {
				action = new BoardListAction();
			}else if (command.equals("member_list")) {
				action = new MypageAction();
			}else if(command.equals("board_view")) {
				action = new BoardViewAction();
			}else if(command.equals("board_write")) {
				action = new BoardWriteAction();
			}else if(command.equals("board_writeForm")) {
				action = new BoardWriteFormAction();
			}else if(command.equals("board_updateForm")) {
				action = new BoardUpdateFormAction();
			}else if(command.equals("board_update")) {
				action = new BoardUpdateAction();
			}
			return action;}
}


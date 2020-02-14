package com.douzone.mysite.action.board;

import com.douzone.mysite.action.main.MainAction;
import com.douzone.mysite.action.user.JoinAction;
import com.douzone.mysite.action.user.JoinFormAction;
import com.douzone.mysite.action.user.JoinSuccessAction;
import com.douzone.mysite.action.user.LoginAction;
import com.douzone.mysite.action.user.LoginFormAction;
import com.douzone.mysite.action.user.LogoutAction;
import com.douzone.mysite.action.user.UpdateAction;
import com.douzone.mysite.action.user.UpdateFormAction;
import com.douzone.web.action.Action;
import com.douzone.web.action.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		switch(actionName) {
		case "list" : return new BoardAction();
		case "writeform" : return new WriteFormAction();
		case "write" : return new WriteAction();
		case "view" : return new View();
		case "modifyform" : return new ModifyFormAction();
		case "modify" : return new ModifyAction();
		case "delete" : return new BoardDeleteAction();
		case "replyform" : return new ReplyFormAction();
		case "reply" : return new ReplyAction();
		default : return new MainAction();
		}
	}

}

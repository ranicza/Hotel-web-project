package com.epam.te.hotel.logic;

import java.util.HashMap;
import java.util.Map;

import com.epam.te.hotel.logic.impl.admin.AddApartmentCommand;
import com.epam.te.hotel.logic.impl.admin.AddApartmentFormCommand;
import com.epam.te.hotel.logic.impl.admin.ApplyApartmentDataCommand;
import com.epam.te.hotel.logic.impl.user.ApplyUserInfoCommand;
import com.epam.te.hotel.logic.impl.admin.ChangeDataApartmentCommand;
import com.epam.te.hotel.logic.impl.admin.ChangeRoleCommand;
import com.epam.te.hotel.logic.impl.admin.ChangeStatusOrderCommand;
import com.epam.te.hotel.logic.impl.client.CreateOrderCommand;
import com.epam.te.hotel.logic.impl.admin.DeleteApartmentCommand;
import com.epam.te.hotel.logic.impl.client.DeleteOrderCommand;
import com.epam.te.hotel.logic.impl.user.EditUserInfoCommand;
import com.epam.te.hotel.logic.impl.client.FindApartmentCommand;
import com.epam.te.hotel.logic.impl.LanguageCommand;
import com.epam.te.hotel.logic.impl.LogOutCommand;
import com.epam.te.hotel.logic.impl.LoginCommand;
import com.epam.te.hotel.logic.impl.NoSuchCommand;
import com.epam.te.hotel.logic.impl.admin.OrderAdministrationCommand;
import com.epam.te.hotel.logic.impl.client.PaymentCommand;
import com.epam.te.hotel.logic.impl.RegistrationCommand;
import com.epam.te.hotel.logic.impl.admin.ShowAllApartmentCommand;
import com.epam.te.hotel.logic.impl.client.ShowClientBillCommand;
import com.epam.te.hotel.logic.impl.client.ShowClientOrderCommand;
import com.epam.te.hotel.logic.impl.client.ShowFindApartmentFormCommand;
import com.epam.te.hotel.logic.impl.admin.UserAdministrationCommand;

/**
 * Finds command.
 */
public final class CommandHelper {
	
private static final  CommandHelper instance = new CommandHelper();
	
	private Map<CommandName, ICommand> commands = new HashMap<>();
	
	public static CommandHelper getInstance(){
		return instance;
	}
	
	 /**Action commands.
	  * <br/>
	  * Fill the Map with values of names of the commands and actions
	  * which they must to execute.
    */
	public CommandHelper(){
		
			//Everyone commands
			commands.put(CommandName.LANGUAGE, new LanguageCommand());
			commands.put(CommandName.LOG_IN, new LoginCommand());
			commands.put(CommandName.REGISTRATION, new RegistrationCommand());	
			commands.put(CommandName.LOG_OUT, new LogOutCommand());
			commands.put(CommandName.NO_SUCH_COMMAND, new NoSuchCommand());
			
			//Client commands
			commands.put(CommandName.SHOW_CLIENT_BILL, new ShowClientBillCommand());
			commands.put(CommandName.PAYMENT, new PaymentCommand());
			commands.put(CommandName.SHOW_CLIENT_ORDER, new ShowClientOrderCommand());
			commands.put(CommandName.DELETE_ORDER, new DeleteOrderCommand());
			commands.put(CommandName.SHOW_FIND_APARTMENT_FORM, new ShowFindApartmentFormCommand());
			commands.put(CommandName.FIND_APARTMENT, new FindApartmentCommand());
			commands.put(CommandName.CREATE_ORDER, new CreateOrderCommand());
			
			//User (admin & client) commands
			commands.put(CommandName.EDIT_USER_INFO, new EditUserInfoCommand());
			commands.put(CommandName.APPLY_USER_INFO, new ApplyUserInfoCommand());
			
			//Admin commands
			commands.put(CommandName.ROOM_ADMINISTRATION, new ShowAllApartmentCommand());
			commands.put(CommandName.ADD_NEW_APARTMENT_FORM, new AddApartmentFormCommand());
			commands.put(CommandName.ADD_APARTMENT, new AddApartmentCommand());
			commands.put(CommandName.CHANGE_DATA_APARTMENT, new ChangeDataApartmentCommand());
			commands.put(CommandName.APPLY_APARTMENT_DATA, new ApplyApartmentDataCommand());
			commands.put(CommandName.DELETE_APARTMENT, new DeleteApartmentCommand());	
			commands.put(CommandName.ORDER_ADMINISTRATION, new OrderAdministrationCommand());
			commands.put(CommandName.SET_STATUS_ORDER, new ChangeStatusOrderCommand());
			commands.put(CommandName.USER_ADMINISTRATION, new UserAdministrationCommand());
			commands.put(CommandName.CHANGE_ROLE, new ChangeRoleCommand());
	}
	
	/**
	 * Find necessary command by name.
	 * @param commandName
	 * @return the command for some action
	 * @throws InterruptedException
	 */
	public ICommand getCommand(String commandName) throws InterruptedException{	
		CommandName name = CommandName.valueOf(commandName.toUpperCase());
		ICommand command;
		
		if (null != name){
			command = commands.get(name);
		}else{
			command = commands.get(CommandName.NO_SUCH_COMMAND);
		}			
		return command;

}	
}

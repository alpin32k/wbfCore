package alpine.wbf.core.commands.tabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class HomeTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {

        if(command.getLabel().contains("home")){
            List<String> homeArguments = new ArrayList<>();

            if(args.length == 1){
                homeArguments.add("add");
                homeArguments.add("del");
                homeArguments.add("list");
            }

            return homeArguments;
        }

        return null;
    }
}

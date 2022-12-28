package alpine.wbf.core.commands.tabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TimeTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {

        if(command.getLabel().contains("time")){
            List<String> timeArguments = new ArrayList<>();

            if(args.length == 1){
                timeArguments.add("day");
                timeArguments.add("noon");
                timeArguments.add("night");
            }

            return timeArguments;
        }

        return null;
    }
}

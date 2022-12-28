package alpine.wbf.core.commands.tabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class WeatherTabCompleter implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {

        if(command.getLabel().contains("weather")){
            List<String> weatherArguments = new ArrayList<>();

            if(args.length == 1){
                weatherArguments.add("clear");
                weatherArguments.add("rain");
            }

            return weatherArguments;
        }

        return null;
    }
}

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Solution {
    private final static class InputLoader {
        private final String name;
        private final int id;
        private final String findIt;

        public InputLoader(final String name, final int id, final String findIt) {
            this.name = name;
            this.id = id;
            this.findIt = findIt;
        }

        public boolean isAlpha(String name) {
            return name.matches("[a-zA-Z\\-]+");
        }

        public ArrayList<Input> load() {
            ArrayList<Input> buffer = new ArrayList<>();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(name));
                String line = reader.readLine();
                while (line != null) {
                    if (line.contains(findIt) || line.contains(findIt.
                            toUpperCase().charAt(0) + findIt.substring(1))
                            || line.contains(findIt.toUpperCase())) {
                        String[] parts = line.split(" |\\t");
                        for (int j = 0; j < parts.length; j++) {
                            if (parts[j].length() < findIt.length()) {
                                continue;
                            }
                            String name = parts[j].toLowerCase();
                            if (name.contains("'")) {
                                if ((j - 1) > 0 && (parts[j - 1].equals("a") ||
                                        parts[j - 1].equals("the"))) {
                                    name = name.replace("'s", "");
                                } else continue;
                            }
                            if (name.contains("-")) {
                                String parts2[] = name.split("-");
                                for (String str : parts2) {
                                    if (str.startsWith(findIt)) {
                                        Input in;
                                        if (!isAlpha(name)) {
                                            name = name.replaceAll("[^a-zA-Z\\-]+", "");
                                            in = new Input(id, name);
                                        } else {
                                            in = new Input(id, name);
                                        }
                                        if (!buffer.contains(in)) {
                                            buffer.add(in);
                                        }
                                        continue;
                                    }
                                }
                            }
                            String substring1 = parts[j].toLowerCase().substring(0, findIt.length());
                            if (substring1.equals(findIt)) {
                                Input elem;
                                if (!isAlpha(name)) {
                                    name = name.replaceAll("[^a-zA-Z\\-]+", "");
                                    elem = new Input(id, name);
                                } else {
                                    elem = new Input(id, name);
                                }
                                if (!buffer.contains(elem)) {
                                    buffer.add(elem);
                                }
                            }
                        }
                    }
                    line = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return buffer;
        }

    }

    public static void main(String[] args) {
        int i = 0;
        Scanner stdIo = new Scanner(System.in);
        String exit = "/exit";
        while (!stdIo.hasNext(exit)) {
            String word = stdIo.next();
            ArrayList<Input> suggestions = new ArrayList<>();
            for (int j = 0; j < args.length; j++) {
                InputLoader in = new InputLoader(args[j], j + 1, word);
                suggestions.addAll(in.load());
            }

            if (suggestions.size() == 0) {
                System.out.println("No suggestions...");
            } else {
                Collections.sort(suggestions);
                ArrayList<Input> finalSuggestions = new ArrayList<>();
                Input prev = suggestions.get(0);
                finalSuggestions.add(prev);
                for (int j = 1; j < suggestions.size(); j++) {
                    if (!suggestions.get(j).equals(prev)
                            || !finalSuggestions.contains(suggestions.get(j))) {
                        finalSuggestions.add(suggestions.get(j));
                    } else {
                        prev.getDuplicateIn().add(suggestions.get(j).getId());
                        continue;
                    }
                    prev = suggestions.get(j);
                }
                int counter = 0;
                for (Input in : finalSuggestions) {
                    counter++;
                    if (counter > 5) {
                        break;
                    }
                    if (in.getDuplicateIn().size() >= 1) {
                        System.out.println(in.getWord().toLowerCase() + " : " + in.getId() + " " + in.getDuplicateIn()
                                .toString().replace(",", "")  //remove the commas
                                .replace("[", "")
                                .replace("]", "")
                                .trim());
                    } else System.out.println(in.getWord().toLowerCase() + " : " + in.getId());
                }
                for (Input in : finalSuggestions) {
                    in.setDuplicateIn(new ArrayList());
                }
            }
            System.out.println();
            stdIo.nextLine();
        }
    }
}
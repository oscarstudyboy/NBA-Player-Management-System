import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class playerPage extends JFrame {
    // create a map method to mapping array from api by player id
    public Map lookupplayer(String id) {
        Map<String,String> infor = new HashMap<String,String>();
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("https://api-nba-v1.p.rapidapi.com/players?id=%s",id)))
                    .header("content-type", "application/octet-stream")
                    .header("X-RapidAPI-Key", "57b1f5f97emshde22b2ccea847aep17bb66jsn0d0af47c1dd9")
                    .header("X-RapidAPI-Host", "api-nba-v1.p.rapidapi.com")
                    .method("GET", HttpRequest.BodyPublishers.noBody())
                    .build();

            HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());


            // parse JSON string to JSON object
            JSONObject obj = new JSONObject(response.body());
            // get players array from JSON object
            JSONArray array = (JSONArray) obj.get("response");
            //System.out.println(array);

            /*parsing multiple level of arrays from api
            * check is the key value in the first array*/
            JSONObject element1 = (JSONObject) array.get(0);
            Iterator key1s = element1.keys();
            while (key1s.hasNext()){
                String key1 = (String) key1s.next();
                if (element1.get(key1) == null) {
                    infor.put((String) key1, "");
                } else {
            /*if the key is not in the first array,
            * keep check the second array*/
                    if (element1.get(key1) instanceof JSONObject) {
                        JSONObject element2 = (JSONObject) element1.get(key1);
                        Iterator key2s = element2.keys();
                        while (key2s.hasNext()) {
                            String key2 = (String) key2s.next();
                            if (element2.get(key2) == null) {
                                infor.put((String) key2, "");
                            } else {
                                infor.put((String) key2, element2.get(key2).toString());
                            }

                        }
                        continue;
                    }

                    infor.put((String) key1, element1.get(key1).toString());
                }

            }
        }catch (Exception e){
            System.out.println("false"+e);
        }//if the request is success

        return infor;
    }


    //define different value
    private ImageIcon imageIcon = new ImageIcon("D:\\Java files\\nbaPlayer manager system\\src\\player face.png");
    //logo in player page
        private JLabel playerimage = new JLabel(imageIcon);
        private JPanel panel1 = new JPanel();

        //create a panel for player's name
        private JPanel playerName = new JPanel();
        private JLabel fName = new JLabel("firstname");
        private JLabel lName = new JLabel("last name");
    //create a panel for player's birthday
        private JPanel birthday = new JPanel();
        private JLabel birth = new JLabel("birthday");
    //create a panel for player's citizenship
        private JPanel country = new JPanel();
        private JLabel home = new JLabel("country");
        private JPanel playerID = new JPanel();
    //create a panel for player's id
        private JLabel getID = new JLabel("player ID");
    //create a panel for player's start year
        private JPanel year = new JPanel();
        private JLabel yearStart = new JLabel("start");
    //create a panel for player's year played
        private JPanel pro = new JPanel();
        private JLabel proYear = new JLabel("pro");
    //create a panel for player's height
        private JPanel height = new JPanel();
        private JLabel tall = new JLabel("height");
    //create a panel for player's weight
        private JPanel weight = new JPanel();
        private JLabel fat = new JLabel("weight");
    //create a panel for player's affiliation
        private JPanel jersey = new JPanel();
        private JLabel nb = new JLabel("affiliation");
    //create a panel for player's college
        private JPanel college = new JPanel();
        private  JLabel school = new JLabel("college");


        public playerPage(String id) {
            // Set frame properties
            setTitle("player basic information");
            setSize(800, 500);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null);

            //mapping string from JSONobject to jframe
            Map<String,String> playerinfor = lookupplayer(id);
            // Create panel object and set up their properties
            panel1.setBounds(10, 50, 200, 200);
            panel1.setLayout(new BoxLayout(panel1,BoxLayout.Y_AXIS));
            panel1.add(playerimage);
            playerName.setBounds(50, 260, 100, 100);
            fName = new JLabel(playerinfor.get("firstname"));
            lName = new JLabel(playerinfor.get("lastname"));
            playerName.add(fName);
            playerName.add(lName);

            Border blackline = BorderFactory.createLineBorder(Color.black);

            birthday.setBounds(220,80,250,25);
            birthday.setBorder(blackline);
            birth = new JLabel(playerinfor.get("date"));
            birthday.add(birth);

            country.setBounds(490,80,250,25);
            country.setBorder(blackline);
            home = new JLabel(playerinfor.get("country"));
            country.add(home);

            playerID.setBounds(350,50,250,25);
            playerID.setBorder(blackline);
            getID = new JLabel(playerinfor.get("id"));
            playerID.add(getID);


            year.setBounds(220,120,250,25);
            year.setBorder(blackline);
            yearStart = new JLabel(playerinfor.get("start"));
            year.add(yearStart);

            pro.setBounds(490,120,250,25);
            pro.setBorder(blackline);
            proYear = new JLabel(playerinfor.get("pro"));
            pro.add(proYear);

            height.setBounds(220,160,250,25);
            height.setBorder(blackline);
            tall = new JLabel(playerinfor.get("feets")+"'"+playerinfor.get("inches"));
            height.add(tall);


            weight.setBounds(490,160,250,25);
            weight.setBorder(blackline);
            fat = new JLabel(playerinfor.get("pounds"));
            weight.add(fat);


            jersey.setBounds(220,200,250,25);
            jersey.setBorder(blackline);
            nb = new JLabel(playerinfor.get("affiliation"));
            jersey.add(nb);

            college.setBounds(490,200,250,25);
            college.setBorder(blackline);
            school = new JLabel(playerinfor.get("college"));
            college.add(school);

            // go back to main page when press back button
            JPanel back = new JPanel();
            back.setBounds(350,240,250,50);
            JButton goback= new JButton("back");
            goback.setPreferredSize(new Dimension(80,30));
            goback.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent e){
                        gui newFrame = new gui();
                        setVisible(false);
                        newFrame.setVisible(true);

                    }
                });
            back.add(goback);
            // Add panel to frame
            add(panel1);
            add(birthday);
            add(country);
            add(year);
            add(pro);
            add(height);
            add(weight);
            add(jersey);
            add(college);
            add(playerID);
            add(playerName);
            add(back);
            // Display frame
            setLayout(null);
            setVisible(true);
            repaint();
        }

        public static void main(String[] args) {
            new playerPage("");
        }
    }



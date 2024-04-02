import java.io.*;
public class GuitarHero {
    public static void main(String[] args){
        GuitarHero g = new GuitarHero();
    }

    public GuitarHero(){
        File file = new File("GuitarHero.txt");

        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String temp;

            while((temp=reader.readLine()) != null){
                String[] start = temp.split(",");

                String[][] music = new String[38][start.length + 1];
                

                String[][] notes = {{"E","A","D","G","B","E"},
                                    {"F","A#","D#","G#","C","F"},
                                    {"F#","B","E","A","C#","F#"},
                                    {"G","C","F","A#","D","G"},
                                    {"G#","C#","F#","B","D#","G#"}};

                

                String[][] sheet = new String[5][start.length * 6];
                
                for(int r=0;r<5;r++){
                    String[] pieces = temp.split(",");
                    for(int c=0;c<pieces.length * 6;c+=6){
                        for(int x=0;x<6;x++){
                            sheet[r][c+x] = String.valueOf(pieces[c/6].charAt(x));
                        }
                    }
                    temp=reader.readLine();

                }

                for(int c=0;c<start.length * 6;c+=6){
                    int count = music.length - 1;
                    boolean playB = false;
                    for(int x=0;x<6;x++){
                        for(int r=0;r<5;r++){
                            if(count>=0){
                                if(r == 4 && x == 3){
                                    if(sheet[0][c+4].equals("o") || sheet[4][c+3].equals("*")){
                                        playB = true;
                                    }
                                }
                                else if(r == 0 && x == 4){
                                    music[count][0] = "B";
                                    if(playB){
                                        music[count][(c/6)+1] = "O";
                                        count--;
                                    }
                                    else{
                                        music[count][(c/6)+1] = " ";
                                        count--;
                                    }
                                }
                                else{
                                    music[count][0] = notes[r][x];
                                    if(sheet[r][c+x].equals("o") || sheet[r][c+x].equals("*")){
                                        music[count][(c/6)+1] = "O";
                                        count--;
                                    }
                                    else{
                                        music[count][(c/6)+1] = " ";
                                        count--;
                                    }
                                }
                            }
                        }
                    }
                }

                printArray(music);


            }
        }
        catch(IOException e){

        }

    }

    public void printArray(String[][] a){

        System.out.println("Measure\t\t1\t2\t3\t4\t5\t6\t7");
        for(int r=9;r<a.length;r++){
            for(int c=0;c<a[0].length;c++){
                if(c==0){
                    System.out.print(a[r][c]+"\t\t");
                }
                else{
                    System.out.print(a[r][c]+"\t");
                }
            }
            System.out.println();
        }
    }
}

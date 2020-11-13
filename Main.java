import java.util.Random;
import java.io.IOException;
class Particle 
{
    private int x;
    private int y;
    public Particle (){
        
    }
    public void set_x (int xx)
    {
        x = xx;
    }
    public int get_x()
    {
        return x;
    }
    public void set_y (int yy)
    {
        y = yy;
    }
    public int get_y()
    {
        return y;
    }
}
class Box
{
  private int height;
  private int width;
  private Particle[] list;
  private int count = 0;
  private char [][] mapBox;
  public Box(int height, int width)
  {
    mapBox = new char[height + 1][width + 1];
    list = new Particle[height*width*100];
    for(int i = 0; i < height + 1; i++)
    {
      for(int j = 0; j < width + 1; j++)
      {
        if(i == 0 || i == height)
        {
          mapBox[i][j] ='-';
        }
        else if(j == 0 || j == width)
        {
          mapBox[i][j] ='|';
        }
        else
        {
          mapBox[i][j] = ' ';
        }
      }
    }
    this.height = height;
    this.width = width;
  }
  public int get_count()
  {
    return count;
  }
  public void Particle_create(int x, int y){
    if(y >0  && y < height  && x < width  && x > 0)
    {
      Particle A = new Particle();
      A.set_x(x);
      A.set_y(y);
      list[count] = A;
      mapBox[y][x] = '*';
      count++;
    }
    else{
      System.out.println("Create Particle Failed!");
    }
  }
  public void update_map(){
    for(int i = 1; i < height; i++){
      for(int j = 1;j < width; j++){
        mapBox[i][j] =' ';
      }
    }
    for(int i = 0; i < count; i++){
      mapBox[list[i].get_y()][list[i].get_x()] = '*';
    }
  }
  public boolean check_out(int x, int y){
    if(y >0  && y < height  && x < width  && x > 0){
      return true;
    }
    else{
      return false;
    }
  }
  public boolean check_collide(int x, int y){
    if(mapBox[y][x] == '*'){
      return true;
    }
    else{
      return false;
    }
  }
    public boolean check_full()
  {
    for(int i = 1; i < height ; i++)
    {
      for(int j = 1; j < width ; j++)
      {
        if(mapBox[i][j]!='*')
          return false;
      }
    }
    return true;
  }
  public void Create_random(int index){
    Random generator = new Random();
    int valuex = generator.nextInt(width-1) + 1;
    int valuey = generator.nextInt(height-1) + 1;
    list[index].set_x(valuex);
    list[index].set_y(valuey);
    Particle_create(valuex,valuey);
    update_map();
  }
  public void move(){
    Random generator = new Random();
    for(int i = 0; i < count; i++){
      int value =generator.nextInt(8) + 1;
      move_step(value,i);
    }
  }
  public void move_step(int step, int index){
    switch(step){
      case 1: //north
          if(check_collide(list[index].get_x(),list[index].get_y() - 1)){
            Create_random(index);
            break;
          }
          if(check_out(list[index].get_x(), list[index].get_y() -1 )){
            list[index].set_y(list[index].get_y() - 1);
            update_map();
          }
          break;
      case 2: //north west
         if(check_collide(list[index].get_x() + 1, list[index].get_y() -1)){
           Create_random(index);
           break;
         }
         if(check_out(list[index].get_x()+ 1, list[index].get_y() - 1)){
           list[index].set_y(list[index].get_y() -1);
           list[index].set_x(list[index].get_x() +1);
           update_map();
         }
         break;
      case 3: //east
         if(check_collide(list[index].get_x() + 1, list[index].get_y())){
           Create_random(index);
           break;
         }
         if(check_out(list[index].get_x()+1,list[index].get_y())){
           list[index].set_x(list[index].get_x()+ 1);
           update_map();
         }
         break;
      case 4: //  South East
         if(check_collide(list[index].get_x()+1,list[index].get_y()+1)){
           Create_random(index);
           break;
         }
         if(check_out(list[index].get_x()+1,list[index].get_y()+1)){
           list[index].set_y(list[index].get_y()+1);
           list[index].set_x(list[index].get_x()+1);
           update_map();
         }
         break;
      case 5: // South
          if(check_collide(list[index].get_x(),list[index].get_y()+1)){
            Create_random(index);
            break;
          }
          if(check_out(list[index].get_x(),list[index].get_y()+1)){
            list[index].set_y(list[index].get_y()+1);
            update_map();
          }
      case 6: // South West
          if(check_collide(list[index].get_x()-1,list[index].get_y()+1)){
            Create_random(index);
            break;
          }
          if(check_out(list[index].get_x()-1,list[index].get_y()+1)){
            list[index].set_y(list[index].get_y()+1);
            list[index].set_x(list[index].get_x()-1);
            update_map();
          }
          break;
      case 7: // West
          if(check_collide(list[index].get_x()-1,list[index].get_y())){
            Create_random(index);
            break;
          }
          if(check_out(list[index].get_x()-1,list[index].get_y())){
            list[index].set_x(list[index].get_x()-1);
            update_map();
          }
          break;
      case 8: // North West 
          if(check_collide(list[index].get_x()-1,list[index].get_y()-1)){
            list[index].set_x(list[index].get_x()-1);
            update_map();
          }
          if(check_out(list[index].get_x()-1,list[index].get_y()-1)){
            list[index].set_y(list[index].get_y()-1);
            list[index].set_x(list[index].get_x()-1);
            update_map();
          }
          break;
    }
  }
  public void print_map(){
    for(int i = 0; i < height + 1; i++){
      for(int j = 0; j < width + 1; j++){
        System.out.print(mapBox[i][j]);
      }
      System.out.println("");
    }
  }
}
class Main {
  public static void main(String[] args) {
    Box myBox = new Box(15, 20);
    myBox.Particle_create(10,3);
    myBox.Particle_create(5, 5);
    myBox.Particle_create(5, 7);
    myBox.print_map();
    while(!myBox.check_full()){
      try {
       if (System.getProperty("os.name").contains("Windows"))
           new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
       else
           Runtime.getRuntime().exec("clear");
      } catch (IOException | InterruptedException ex) {}
      myBox.move();
      myBox.print_map();
      long original = System.currentTimeMillis();
      while (true) {
          if (System.currentTimeMillis() - original >= 50) {
              break;
          }
      }
    }
  }
}
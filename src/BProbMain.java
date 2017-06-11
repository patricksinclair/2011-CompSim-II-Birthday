import java.io.IOException;


public class BProbMain {
	public static void main(String[] args)throws IOException{

		GroupFrame gf = new GroupFrame();
		Group people = Group.getData();
		people.write();
		people.writeFile();
		people.altWrite();
	}
}

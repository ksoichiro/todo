import org.mindrot.jbcrypt.BCrypt;

// In sbt (activator):
//   [todo] $ project passgen
//   > run password
//   (Hashed password will be shown here)
//   > project root
public class PassGen {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Password string is required");
            System.exit(0);
        }
        String password = args[0];
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(8));
        System.out.println(hashedPassword);
    }
}

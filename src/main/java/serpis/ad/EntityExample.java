package serpis.ad;
import javax.persistence.*;

public class EntityExample {
    @Entity
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private int id;
        private String name;
        private String email;

        public User() {}

        public User(String name, String email) {
            this.setName(name);
            this.setEmail(email);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}

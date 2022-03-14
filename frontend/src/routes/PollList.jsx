import Footer from "../components/layout/Footer";
import Nav from "../components/layout/Nav";
import styles from "./PollList.module.css";

function PollList() {
  return (
    <div>
      <Nav />
      <div className={styles.list_title}> PollList </div>
      <Footer />
    </div>
  );
}

export default PollList;

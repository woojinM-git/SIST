import Image from "next/image";
import styles from "./page.module.css";
import Link from "next/link";

export default function Home() {
  return (
    <div className={styles.page}>
      <h1 className={styles.title}>쌍용교육센터</h1>
      <Link href="/emp">[사원목록]</Link>
    </div>
  );
}

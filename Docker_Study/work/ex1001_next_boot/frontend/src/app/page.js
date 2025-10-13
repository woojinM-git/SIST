import Image from "next/image";
import styles from "./page.module.css";
import Link from "next/link";

export default function Home() {
  return (
    <div className={styles.page}>
      <main className={styles.main}>
        <h1>NextJS+SpringBoot 도커컴포즈 연습</h1>
        <Link href="/emp">[사원목록]</Link>
      </main>
    </div>
  );
}

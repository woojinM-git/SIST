"use client"

import Image from "next/image";
import styles from "./page.module.css";
import { useState } from "react";

export default function Home() {
  let title = "SIST";
  let title2 = "쌍용교육센터";
  let red_color = "redcolor";
  const [sub, setSub] = useState("woojin");
  return (
    <div className={styles.page}>
      <h1 className="title">{title}</h1>
      <h1 className={styles.red_color}>{title}</h1>
      <h1>{sub}</h1>
    </div>
  );
}

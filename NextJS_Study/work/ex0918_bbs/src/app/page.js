"use client"

import styles from "./page.module.css";
import { Divider } from "@mui/material";



export default function Home() {
  
  return (
    <div className={styles.page}>
      <div style={{width: '80%', margin: 'auto', padding: '20px', textAlign: 'center'}}>
        <h1 style={{marginTop: '50px'}}>메인 페이지</h1>
      </div>
    </div>
  );
}

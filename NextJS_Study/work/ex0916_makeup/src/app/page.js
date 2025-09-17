"use client"

import Image from "next/image";
import styles from "./page.module.css";
import axios from "axios";
import { useEffect, useState } from "react";
import ItemList from "@/components/ItemList";
import { Divider } from "@mui/material";



export default function Home() {
  const api_url = "/makeup/v1/products.json?brand=maybelline";

  const [list, setList] = useState([]);

  function callApi(){
    axios.get(api_url).then(function(res){
      /* 요청에 성공했을 때 수행하는 곳 */
      // console.log(data.data);
      setList(res.data);
      console.log(list);
    });
  }

  useEffect(function(){
    callApi();
  }, []); // 현재 페이지가 읽혀질 때 한번 호출함!

  useEffect(() => {
  console.log("list 변경됨:", list);
  }, [list]);

  return (
    <div className={styles.page}>
      <div style={{width: '80%', margin: 'auto', padding: '20px', textAlign: 'center'}}>
        <h2 className={styles.left}>베스트상품</h2>
        <Divider/>
        <ItemList list={list.slice(0,9)}/>

        <h2 className={styles.left}>신상품</h2>
        <Divider/>
        <ItemList list={list.slice(9)}/>
      </div>
    </div>
  );
}

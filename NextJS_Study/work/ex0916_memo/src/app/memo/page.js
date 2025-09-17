"use client"
import MyCard from "@/components/MyCard";
import { Card, CardContent, Divider } from "@mui/material";
import axios from "axios";
import Link from "next/link";
import { useEffect, useState } from "react";

export default function Page() {  
    const api_url = "/memo/all";

    const [list, setList] = useState([]);

    function callAPI(){
        axios.get(api_url).then(function(response){
            setList(response.data.m_list);
        });
    }

    useEffect(()=>{ //익명함수 
        callAPI();
    },[]); // [] : 현재 페이지가 열릴 때 한번 수행!

    return(
        <div className="list-bg">
            <h2>메모장</h2>
            <Divider/>
            <MyCard list={list}/>
        </div>
    );
};
"use client"

import EmpTable from "@/component/EmpTable";
import { Avatar, Card, CardContent, CardHeader } from "@mui/material";
import { red } from "@mui/material/colors";
import axios from "axios";
import { useEffect, useState } from "react";

export default function Emp(){

    const api_url = "/api/emp/all"

    const [list, setList] = useState([]);

    function getData(){
        axios.get(api_url).then(function (res){
            // 서버쪽에서 보내오는 JSON값을 list에 저장
            console.log(res);
            setList(res.data);
        });
    }

    useEffect(function(){
        getData();
    }, [])

    return(
        <div style={{width: "800px", margin: "10px auto"}}>
            <Card>
                    <CardHeader
                    avatar={
                        <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
                            S
                        </Avatar>
                        }
                    title="SIST 사원 목록"
                    subheader="September 14, 2016"
                    />
                    <CardContent>
                        <EmpTable ar={list}/>
                    </CardContent>
            </Card>
        </div>
    );
}
"use client"

import BbsList from '@/components/BbsList';
import axios from 'axios';
import {useEffect, useState} from 'react';

export default function Page() {

    const api_url = "/board/list";

    const [bname, setBname] = useState('BBS');

    // 스프링 서버에서 전달되는 JSON 배열을 저장할 곳;
    const [list, setList] = useState([]);

    // 현재 페이지값
    const [cPage, setCpage] = useState(1); 

    // 총 페이지
    const [totalPage, setTotalPage] = useState(0);

    // 비동기식 통신을 수행하는 함수
    function callData(){
        axios.get(
            api_url,
            {
                params:{bname:bname, cPage:cPage}
            }
        ).then(function(json){
            // console.log(json);
            setList(json.data.ar);
            setTotalPage(json.data.totalPage);
        })
    }

    useEffect(function (){
        callData();
    },[cPage]);

    function changePage(e, p){
        // console.log("changePage" + p);
        setCpage(p);
    }

    return(
        <div style={{width: '80%', margin: 'auto', padding: '20px', textAlign: 'center'}}>
            <BbsList ar={list} tp={totalPage} cp={changePage}/>
            {/* cp라는 이름으로 changePage함수를 전달한다. */}
        </div>
    );
};
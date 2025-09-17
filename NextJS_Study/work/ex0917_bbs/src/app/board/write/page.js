'use client';

import { Button, Paper, Table, TableBody, TableCell, TableContainer, TableRow } from "@mui/material";
import axios from "axios";
import { useRouter } from "next/navigation";
import { useState } from "react";

export default function Write() {
    const router = useRouter();

    const api_url = "/board/add";

    // 서버로 보낼 파라미터 값들을 객체로 준비한다.
    const [vo, setVO] = useState({
        subject: "",
        writer: "",
        content: ""
    });

    function changeVO(e){
        // 목적은 useState의 vo 객체를 변경하는 것이다.
        // 그렇다면 vo를 복사해야 한다.
        let bbs = {...vo};
        let value = e.target.value; // 사용자가 입력한 값!
        let name = e.target.name; // 사용자가 입력한 input의 name
        console.log(name + ":" + value)
        bbs[name] = value; // 객체의 내용을 바꾼다
        setVO(bbs); // useState의 값을 최종적으로 변경하자 *******
    }

    function saveData(){
        // 비동기식 통신
        axios.post(api_url,
            {
                subject:vo.subject, 
                writer:vo.writer, 
                content:vo.content,
                bname: "BBS"
            }
        ).then(function(json){
            console.log(vo.subject)
            if(json.data.cnt == 1) // 이 조건을 만족해야만 저장된거임
                router.push("/"); // 메인페이지로 이동
            if(json.data.cnt != 1)
                console.log("저장안됌")
        })
    }

    return(
        <div style={{width: '80%', margin: 'auto', padding: '20px', textAlign: 'center'}}>
            <TableContainer component={Paper}>
                <Table sx={{ minWidth: 650 }}>
                    <TableBody>
                        <TableRow>
                            <TableCell>제목</TableCell>
                            <TableCell>
                                <input type="text" name="subject" onChange={changeVO}/>
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>글쓴이</TableCell>
                            <TableCell>
                                <input type="text" name="writer" onChange={changeVO}/>
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell>내용</TableCell>
                            <TableCell>
                                <textarea name="content" rows={7} cols={50} onChange={changeVO}></textarea>
                            </TableCell>
                        </TableRow>
                        <TableRow>
                            <TableCell colSpan={2}>
                                <Button variant="contained" color="primary" onClick={saveData}>
                                    저장
                                </Button>
                            </TableCell>
                        </TableRow>
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}
"use client"

import { useRouter } from "next/navigation";
import styles from "../../page.module.css";
import { useEffect, useState } from "react";
import axios from "axios";
import tokenStore from "@/app/store/TokenStore";

export default function Memvers(){
    const router = useRouter();

    const [member, setMember] = useState({});

    const {accessToken, setToken} = tokenStore();

    let api_url = "/api/members/login"

    function signIn(){
        axios.post(
            api_url, member,
            {
                withCredentials: true, //이거 없으면 쿠키가 안들어온다. 인증헤더 증명서를 내포하는 자격인증(true) 사용
                header:{
                    "Content-Type":"application/json"
                }
            }
        ).then(function(res){
            if(res.status == 200){
                setToken(res.data.data.accessToken);
                router.push("/"); // 메인화면으로 이동
            }
        });
    }

    useEffect(() => {
        // 이 블록은 브라우저에서만 실행됨
        window.localStorage.setItem("accessToken", accessToken);
    }, [accessToken]);

    function handleChange(e){
        // 사용자가 입력한 값들을 객체로 변경하는 함수
        let {name, value} = e.target;
        setMember({...member, [name]:value});
    }

    return(
        <div style={{width: "80%", margin: '100px auto'}}>
            <h1>로그인</h1>
            <hr/>
            <div style={{wirth: '250px', margin: '10px auto'}}>
            <table>
                <tbody>
                    <tr>
                        <td>
                            <label htmlFor="u_id">사용자ID</label>
                        </td>
                        <td>
                            <input type="text" id="u_id" name="mid" onChange={handleChange}/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label htmlFor="u_pw">사용자PW</label>
                        </td>
                        <td>
                            <input type="password" id="u_pw" name="mpw" onChange={handleChange}/>
                        </td>
                    </tr>
                </tbody>
                <tfoot>
                    <tr>
                        <td style={{columnSpan: 2}} className={styles.txtCenter}>
                            <button type="button" onClick={signIn}>
                                로그인
                            </button>
                        </td>
                    </tr>
                </tfoot>
            </table>
            </div>
        </div>
    );
}
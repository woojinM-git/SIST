"use client"

import Link from "next/link";
// import { cookies } from "next/headers";
import tokenStore from "@/app/store/TokenStore";
import axios from "axios";
import { useRouter } from "next/navigation";


export default function Header() {
    // const cookieStore = await cookies();
    // const accessToken = cookieStore.get("accessToken")?.value;
    // cookieStore.get("accessToken")의 값이 있으면 accessToken변수에
    // 쿠키에 있는 "accessToken"값이 저장되지만 없으면 undifined가 저장된다.

    // 상태관리에 필요한 객체 준비
    const {accessToken, setToken} = tokenStore();

    const router = useRouter();

    let api_url = "/api/members/logout"

    function logout(){
        console.log(window.localStorage.getItem("accessToken"))
        // 컴펌을 받자!~
        if(confirm("로그아웃하시나요?")){
            // 컨펌창에서 참을 선택했을 때만 수행
            axios.post(api_url).then(function(res){
                if(res.status == 200){
                    // 성공했다면 쿠키가 삭제된거임
                    console.log("로그아웃");
                    setToken(null);

                    window.localStorage.removeItem("accessToken");
                    router.push("/"); // 메인화면으로 이동
                    console.log("로컬스토리지 삭제됌"+window.localStorage.getItem("accessToken"))
                }
            });
        }
        // confirm에서 아니요를 선택했을 때 수행
    }

    

    return(
        <nav id="header" style={{position: "relative", margin: "20px"}}>
            <div style={{display: "flex", position: "absolute", left: "10px"}}>
                <Link href="/">
                    <div>Home</div>
                </Link>
                <Link href="/members">
                    <div style={{paddingLeft: "10px"}}>Members</div>
                </Link>
                <Link href="/board">
                    <div style={{paddingLeft: "10px"}}>Board</div>
                </Link>
            </div>

            <div style={{display: "flex", position: "absolute", right: "10px"}}>
                {
                    accessToken == null
                    ? <Link href="/members/login">
                        <div style={{paddingLeft: "10px"}}>Login</div>
                    </Link>

                    : <Link href="" onClick={logout}>
                        <div style={{paddingLeft: "10px"}}>Logout</div>
                    </Link>

                }
                <Link href="/members/signup">
                    <div style={{paddingLeft: "10px"}}>Signup</div>
                </Link>
            </div>
        </nav>
    );
};
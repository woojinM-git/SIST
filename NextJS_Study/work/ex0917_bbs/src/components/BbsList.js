import { Box, Button, Pagination, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import { useRouter } from "next/navigation";

export default function BbsList({ar, tp, cp}){

    const router = useRouter();

    return(
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }}>
                <TableHead>
                    <TableRow>
                        <TableCell align="center">번호</TableCell>
                        <TableCell align="center">제목</TableCell>
                        <TableCell align="center">글쓴이</TableCell>
                        <TableCell align="center">날짜</TableCell>
                        <TableCell align="center">조회수</TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {ar.map((row, i) => (
                        <TableRow
                        key={i}
                        sx={{ '&:last-child td, &:last-child th': { border: 0 } }}
                        >
                        <TableCell component="th" scope="row" align="center">
                            {row.b_idx}
                        </TableCell>
                        <TableCell align="center">{row.subject}</TableCell>
                        <TableCell align="center">{row.writer}</TableCell>
                        <TableCell align="center">{row.write_date}</TableCell>
                        <TableCell align="center">{row.hit}</TableCell>
                        </TableRow>
                    ))}
                    {/* 다음은 페이징 기법을 위한 행이 하나 추가되어야 한다. */}
                    <TableRow>
                        <TableCell colSpan={4} sx={{ p: 0 }}>
                            <Box sx={{ display: "flex", justifyContent: "center" }}>
                                <Pagination count={tp} color="primary" onChange={cp} />
                            </Box>
                        </TableCell>
                        <TableCell align="right">
                            <Button variant="contained" color="primary" onClick={function(){
                                // 현재 글쓰기 버튼을 클릭할 때마다 수행하는 곳!
                                router.push("/board/write");

                                // Link : 이동할 페이지 경로가 정해졌을 때 사용
                                // router.push : 이동하기 전에 조건 또는 구현되는 로직에 따라
                                //                  경로가 변경될 때
                                }}>글쓰기</Button>
                        </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    );
}
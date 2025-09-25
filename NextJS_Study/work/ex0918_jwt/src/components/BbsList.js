import { Box, Button, Pagination, Paper, Table, TableBody, TableCell, TableContainer, TableHead, TableRow } from "@mui/material";
import { useRouter } from "next/navigation";
import { useCallback, useMemo } from "react";

export default function BbsList({ar, tp, cp}){

    const router = useRouter();

    // 테이블 헤더 정의 (중복 제거)
    const columns = useMemo(() => (
        ["번호", "제목", "글쓴이", "날짜", "조회수"]
    ), []);

    // 이벤트 핸들러 분리
    const handleWriteClick = useCallback(() => {
        router.push("/board/write");
    }, [router]);

    const handlePageChange = useCallback((event, page) => {
        // 부모에서 전달된 페이지 변경 콜백 호출
        if (typeof cp === "function") {
            cp(event, page);
        }
    }, [cp]);

    return(
        <TableContainer component={Paper}>
            <Table sx={{ minWidth: 650 }}>
                <TableHead>
                    <TableRow>
                        {columns.map((title) => (
                            <TableCell key={title} align="center">{title}</TableCell>
                        ))}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {ar.map((row) => (
                        <TableRow
                        key={row.b_idx}
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
                                <Pagination
                                    count={tp}
                                    color="primary"
                                    onChange={handlePageChange}
                                />
                            </Box>
                        </TableCell>
                        <TableCell align="right">
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={handleWriteClick}
                            >
                                글쓰기
                            </Button>
                        </TableCell>
                    </TableRow>
                </TableBody>
            </Table>
        </TableContainer>
    );
}
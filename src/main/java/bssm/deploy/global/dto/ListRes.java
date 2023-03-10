package bssm.deploy.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class ListRes<T> {

    private List<T> list;
    private long size;

    public static <T> ListRes<T> create(List<T> list) {
        return new ListRes<>(list, list.size());
    }

}

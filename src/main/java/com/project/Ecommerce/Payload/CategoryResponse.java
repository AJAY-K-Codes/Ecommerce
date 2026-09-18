package com.project.Ecommerce.Payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class CategoryResponse
{
    private List<CategoryDTO> content;
    private int pageNumber;
    private int pagesize;
    private Long totalElemnts;
    private int totalpages;
    private Boolean Lastpage;
}

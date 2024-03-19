package exercise.mapper;

import exercise.dto.ProductCreateDTO;
import exercise.dto.ProductDTO;
import exercise.dto.ProductUpdateDTO;
import exercise.model.Product;
import org.mapstruct.*;

// BEGIN
@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class ProductMapper{

    /**
     * map(PostCreateDTO dto): принимает объект типа PostCreateDTO и возвращает объект типа Post. Этот метод используется для создания нового объекта Post на основе данных из PostCreateDTO.
     * map(Post model): принимает объект типа Post и возвращает объект типа PostDTO. Этот метод используется для преобразования объекта Post в объект PostDTO, который будет использоваться для передачи данных клиенту.
     * update(PostUpdateDTO dto, @MappingTarget Post model): принимает объект типа PostUpdateDTO и объект типа Post в качестве параметров. Этот метод используется для обновления существующего объекта Post на основе данных из PostUpdateDTO.
     */

    @Mapping(target = "name", source = "title")
    @Mapping(target = "cost", source = "price")
    @Mapping(target = "barcode", source = "vendorCode")
    public abstract Product map(ProductCreateDTO dto);

    @Mapping(target = "title", source = "name")
    @Mapping(target = "price", source = "cost")
    @Mapping(target = "vendorCode", source = "barcode")
    public abstract ProductDTO map(Product model);


    @Mapping(target = "cost", source = "price")
    public abstract void update(ProductUpdateDTO dto, @MappingTarget Product model);

}
// END

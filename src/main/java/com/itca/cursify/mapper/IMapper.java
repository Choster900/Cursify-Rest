package com.itca.cursify.mapper;

public interface IMapper <Input,Output>{
    public Output map(Input in);
}

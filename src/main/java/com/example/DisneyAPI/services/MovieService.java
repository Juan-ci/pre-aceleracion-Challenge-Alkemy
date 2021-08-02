package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.MovieModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.IMovieRepository;

@Service
public class MovieService {
    
    @Autowired
    IMovieRepository movieRepository;
    
    @Transactional(readOnly = true)
    public ArrayList<MovieModel> getMovie() {
        return (ArrayList<MovieModel>) movieRepository.findAll();
    }
    
    @Transactional
    public MovieModel saveMovie(MovieModel movie) {
        return movieRepository.saveAndFlush(movie);
    }
    
    @Transactional
    public boolean deleteMovie(Long id) {
        try {
            movieRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
package br.dev.s2w.alura.flix.domain.service.impl

import br.dev.s2w.alura.flix.domain.gateway.video.*
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService

class VideoServiceImpl(
    private val findAllVideosGateway: FindAllVideosGateway,
    private val findAllVideosByCategoriaGateway: FindAllVideosByCategoriaGateway,
    private val findAllVideosByTituloGateway: FindAllVideosByTituloGateway,
    private val findVideoByIdGateway: FindVideoByIdGateway,
    private val insertVideoGateway: InsertVideoGateway,
    private val updateVideoByIdGateway: UpdateVideoByIdGateway,
    private val deleteVideoByIdGateway: DeleteVideoByIdGateway
) : VideoService {

    override fun retrieve(): List<Video> {
        return findAllVideosGateway.fetch()
    }

    override fun retriveAllByCategoria(id: Long): List<Video> {
        return findAllVideosByCategoriaGateway.fetchByCategoria(id)
    }

    override fun retriveAllByTitulo(titulo: String): List<Video> {
        return findAllVideosByTituloGateway.fetchByTitulo(titulo)
    }

    override fun retriveOneBy(id: Long): Video {
        return findVideoByIdGateway.fetchOneBy(id)
    }

    override fun saveOne(video: Video): Video {
        return insertVideoGateway.saveOne(video)
    }

    override fun modifyOneBy(id: Long, video: Video): Video {
        return updateVideoByIdGateway.modifyOneBy(id, video)
    }

    override fun removeOneBy(id: Long) {
        deleteVideoByIdGateway.removeOneBy(id)
    }
}
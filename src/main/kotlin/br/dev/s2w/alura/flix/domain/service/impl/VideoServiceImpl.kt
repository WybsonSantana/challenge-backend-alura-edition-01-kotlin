package br.dev.s2w.alura.flix.domain.service.impl

import br.dev.s2w.alura.flix.domain.gateway.FindAllVideos
import br.dev.s2w.alura.flix.domain.gateway.FindVideoById
import br.dev.s2w.alura.flix.domain.gateway.InsertVideo
import br.dev.s2w.alura.flix.domain.gateway.UpdateVideo
import br.dev.s2w.alura.flix.domain.model.Video
import br.dev.s2w.alura.flix.domain.service.VideoService

class VideoServiceImpl(
    private val findAllVideos: FindAllVideos,
    private val findVideoById: FindVideoById,
    private val insertVideo: InsertVideo,
    private val updateVideo: UpdateVideo
) : VideoService {

    override fun retrieve(): List<Video> {
        return findAllVideos.fetch()
    }

    override fun retriveOne(id: Long): Video {
        return findVideoById.fetchOne(id)
    }

    override fun saveOne(video: Video): Video {
        return insertVideo.saveOne(video)
    }

    override fun modifyOne(id: Long, video: Video): Video {
        return updateVideo.modifyOne(id, video)
    }
}
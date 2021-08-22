package dev.mmoreno.brbad.xumak.util

import dev.mmoreno.brbad.xumak.db.entities.BreakingBadCharacterEntity
import dev.mmoreno.brbad.xumak.networking.model.BreakingBadCharacterResponse

/**
 * Extension function for mapping a list of response entities to
 * database entities
 */
fun List<BreakingBadCharacterResponse>.toBreakingBadCharacterEntities(offset: Int) =
  this.mapIndexed { index, character ->
    BreakingBadCharacterEntity(
      id = character.id,
      name = character.name,
      nickname = character.nickname,
      image = character.img,
      isFavorite = false,
      customOffset = offset + index
    )
  }


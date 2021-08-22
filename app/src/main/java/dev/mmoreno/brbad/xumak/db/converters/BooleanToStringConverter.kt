package dev.mmoreno.brbad.xumak.db.converters

import androidx.room.TypeConverter

class BooleanToStringConverter {
  @TypeConverter
  fun fromBooleanToString(isTrue: Boolean) = if (isTrue) "YES" else "NO"

  @TypeConverter
  fun fromStringToBoolean(isTrue: String) = (isTrue == "YES")
}
package com.rootstrap.data.store

sealed class PreferenceException : Exception()

class InvalidTokenException : PreferenceException()
class InvalidValueException : PreferenceException()
class WritingDataException : PreferenceException()

package org.example.sample.core

class BadRequestException(message: String?) : RuntimeException(message)
class NotFoundException(message: String) : RuntimeException(message)
class InternalServerException(message: String) : RuntimeException(message)

class ForbiddenException(message: String) : RuntimeException(message)
class UnauthorizedException(message: String) : RuntimeException(message)

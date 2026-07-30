// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 135 -> sentence 135
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 135 -> sentence 135
 *                declarations, property-declaration, delegated-property-declaration -> paragraph 135 -> sentence 135
 * NUMBER: 1
 * DESCRIPTION: by lazy delegated property is not evaluated during init block execution in class declaration
 */

// TESTCASE NUMBER: 1
class LazyBox {
    val log = mutableListOf<String>()
    val v: Int by lazy {
        log += "lazy"
        1
    }

    init {
        log += "init"
    }
}

// TESTCASE NUMBER: 2
class StringLazyBox {
    val log = mutableListOf<String>()
    val tag: String by lazy {
        log += "lazy"
        "x"
    }

    init {
        log += "init"
    }
}

// TESTCASE NUMBER: 3
class DoubleLazyBox {
    val log = mutableListOf<String>()
    val value: Double by lazy {
        log += "lazy"
        2.0
    }

    init {
        log += "init"
    }
}

fun intLogWithoutAccess(): List<String> = LazyBox().log

fun stringLogWithoutAccess(): List<String> = StringLazyBox().log

fun doubleLogWithoutAccess(): List<String> = DoubleLazyBox().log

fun box(): String {
    if (intLogWithoutAccess() != listOf("init")) return "NOK: int"
    if (stringLogWithoutAccess() != listOf("init")) return "NOK: string"
    if (doubleLogWithoutAccess() != listOf("init")) return "NOK: double"
    return "OK"
}

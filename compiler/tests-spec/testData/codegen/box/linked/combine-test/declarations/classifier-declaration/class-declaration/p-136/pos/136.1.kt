// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 136 -> sentence 136
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 136 -> sentence 136
 *                declarations, property-declaration, delegated-property-declaration -> paragraph 136 -> sentence 136
 * NUMBER: 1
 * DESCRIPTION: init block completes before by lazy delegated property is evaluated on first access in class declaration
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

fun intLogAfterAccess(): List<String> {
    val box = LazyBox()
    box.v
    return box.log
}

fun stringLogAfterAccess(): List<String> {
    val box = StringLazyBox()
    box.tag
    return box.log
}

fun doubleLogAfterAccess(): List<String> {
    val box = DoubleLazyBox()
    box.value
    return box.log
}

fun box(): String {
    if (intLogAfterAccess() != listOf("init", "lazy")) return "NOK: int"
    if (stringLogAfterAccess() != listOf("init", "lazy")) return "NOK: string"
    if (doubleLogAfterAccess() != listOf("init", "lazy")) return "NOK: double"
    return "OK"
}

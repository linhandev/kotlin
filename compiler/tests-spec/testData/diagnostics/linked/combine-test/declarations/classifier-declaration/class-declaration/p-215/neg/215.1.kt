// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 215 -> sentence 215
 * PRIMARY LINKS: inheritance, inheriting -> paragraph 215 -> sentence 215
 *                inheritance, overriding -> paragraph 215 -> sentence 215
 * NUMBER: 1
 * DESCRIPTION: List/Set/Map type-arg differences make f(List<Int>) and f(List<String>) distinct abstract members at the Kotlin level (ABSTRACT_MEMBER_NOT_IMPLEMENTED if either is missing); dual override of both is a JVM erasure clash (CONFLICTING_JVM_DECLARATIONS) outside frontend diagnostics; contrasts with p-214 non-erasing parameter-list overloads
 */

// TESTCASE NUMBER: 1
interface ListIntArg {
    fun f(x: List<Int>)
}

interface ListStringArg {
    fun f(x: List<String>)
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class DualListMissing<!> : ListIntArg, ListStringArg

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class DualListOnlyInt<!> : ListIntArg, ListStringArg {
    override fun f(x: List<Int>) {}
}

// TESTCASE NUMBER: 2
interface SetIntArg {
    fun g(x: Set<Int>)
}

interface SetStringArg {
    fun g(x: Set<String>)
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class DualSetMissing<!> : SetIntArg, SetStringArg

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class DualSetOnlyInt<!> : SetIntArg, SetStringArg {
    override fun g(x: Set<Int>) {}
}

// TESTCASE NUMBER: 3
interface MapIntVal {
    fun h(x: Map<String, Int>)
}

interface MapStringVal {
    fun h(x: Map<String, String>)
}

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class DualMapMissing<!> : MapIntVal, MapStringVal

<!ABSTRACT_MEMBER_NOT_IMPLEMENTED!>class DualMapOnlyInt<!> : MapIntVal, MapStringVal {
    override fun h(x: Map<String, Int>) {}
}

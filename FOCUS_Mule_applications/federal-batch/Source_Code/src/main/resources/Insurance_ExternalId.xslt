<xsl:stylesheet version="3.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" />
<xsl:template match="/">
  <xsl:for-each select="/root/record/ExternalId">
  <xsl:text>'</xsl:text>
  <xsl:value-of select="."/>
  <xsl:text>'</xsl:text>
  <xsl:if test="position() != last()">
  <xsl:text>, </xsl:text>
  </xsl:if>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>
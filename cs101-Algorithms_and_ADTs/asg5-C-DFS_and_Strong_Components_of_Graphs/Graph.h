/*******************************************************************************
 *  $Id: Graph.h,v 1.2 2010-11-22 17:45:36-08 dmfrank - $
 *	Derek Frank, dmfrank@ucsc.edu
 *	
 *	NAME
 *	  Graph.h
 *  
 *	DESCRIPTION
 *	  Header file for the Graph.c ADT.
 ******************************************************************************/

#if !defined(_GRAPH_H_INCLUDE_)
#define _GRAPH_H_INCLUDE_
#define UNDEF -1
#define NIL 0
#define WHITE 0
#define GRAY 1
#define BLACK 2

#include "List.h"

/************* Exported Types *************************************************/

typedef struct Graph* GraphRef;


/************** Constructors-Destructors **************************************/

GraphRef newGraph(int n);
void freeGraph(GraphRef* pG);


/*************** Access Functions *********************************************/

int getOrder(GraphRef G);
int getSize(GraphRef G);
int getCC(GraphRef G);
int getColor(GraphRef G, int u);
int getParent(GraphRef G, int u);
int getDiscover(GraphRef G, int u);
int getFinish(GraphRef G, int u);


/************** Manipulation Procedures ***************************************/

void makeNull(GraphRef G);
void addArc(GraphRef G, int u, int v);
void DFS(GraphRef G, ListRef S);


/***************** Other Operations *******************************************/

GraphRef transpose(GraphRef G);
GraphRef copyGraph(GraphRef G);
void printGraph(FILE* out, GraphRef G);

#endif
